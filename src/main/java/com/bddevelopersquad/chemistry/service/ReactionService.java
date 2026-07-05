package com.bddevelopersquad.chemistry.service;

import com.bddevelopersquad.chemistry.dto.ReactionResponse;
import com.bddevelopersquad.chemistry.dto.SaveReactionRequest;
import com.bddevelopersquad.chemistry.model.Reaction;
import com.bddevelopersquad.chemistry.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;

    public ReactionResponse save(SaveReactionRequest saveReactionRequest) {
        Reaction reaction = new Reaction(
                saveReactionRequest.getSearchKeys(),
                saveReactionRequest.getEquation(),
                saveReactionRequest.getDetails()
        );
        Reaction saved = reactionRepository.save(reaction);
        return new ReactionResponse(
                saved.getId(),
                saved.getSearchKeys(),
                saved.getEquation(),
                saved.getDetails(),
                saved.getKeywordCount()
        );
    }

    public List<ReactionResponse> search(List<String> keywords, int limit) {
        List<String> cleanKeywords = keywords.stream()
                .map(this::normalizeToken)
                .filter(k -> !k.isEmpty())
                .toList();

        if (cleanKeywords.isEmpty()) {
            return Collections.emptyList();
        }

        return reactionRepository.findAll().stream()
                .map(reaction -> scoreReaction(reaction, cleanKeywords))
                .filter(Objects::nonNull)
                .sorted(
                        Comparator.<ScoredReaction>comparingInt(sr -> sr.score).reversed()
                                .thenComparingInt(sr -> sr.reaction.getKeywordCount())
                                .thenComparing(sr -> sr.reaction.getId())
                )
                .limit(limit)
                .map(sr -> new ReactionResponse(sr.reaction.getId(), sr.reaction.getSearchKeys(), sr.reaction.getEquation(), sr.reaction.getDetails(), sr.reaction.getKeywordCount()))
                .collect(Collectors.toList());
    }

    private ScoredReaction scoreReaction(Reaction reaction, List<String> keywords) {
        String[] tokens = reaction.getSearchKeys().split("\\s+");
        int total = 0;

        for (String kw : keywords) {
            int best = 0;
            for (String token : tokens) {
                best = Math.max(best, matchScore(token, kw));
            }
            if (best == 0) {
                return null;
            }
            total += best;
        }
        return new ScoredReaction(reaction, total);
    }

    private int matchScore(String token, String keyword) {
        if (token.equals(keyword)) return 4;
        if (token.startsWith(keyword)) return 3;
        if (token.endsWith(keyword)) return 2;
        if (token.contains(keyword)) return 1;
        return 0;
    }

    private String normalizeToken(String keyword) {
        return keyword.trim().toLowerCase();
    }

    private record ScoredReaction(Reaction reaction, int score) { }

}
