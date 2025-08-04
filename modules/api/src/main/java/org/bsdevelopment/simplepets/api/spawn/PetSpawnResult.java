package org.bsdevelopment.simplepets.api.spawn;

import java.util.Collections;
import java.util.List;

/**
 * Represents the outcome of a pet spawn attempt.
 * <p>
 * This sealed interface restricts implementations to either:
 * <ul>
 *     <li>{@link PetSpawnResult.Success}</li>
 *     <li>{@link PetSpawnResult.Failure}</li>
 * </ul>
 */
public sealed interface PetSpawnResult permits PetSpawnResult.Success, PetSpawnResult.Failure {
    /**
     * Indicates if the pet spawn result was successful or not.
     *
     * @return {@code true} if the spawn was successful, otherwise {@code false}.
     */
    boolean isSuccess();

    /**
     * A record representing a successful spawn operation.
     * <p>
     * No additional data is needed for a success; you can expand as required.
     */
    record Success() implements PetSpawnResult {
        @Override
        public boolean isSuccess() {
            return true;
        }
    }

    /**
     * A record representing a failure to spawn the pet, containing a list
     * of {@link ReasonDetail} objects. Each object pairs a {@link FailureReason}
     * with a detail message.
     */
    record Failure(List<ReasonDetail> reasonDetails) implements PetSpawnResult {

        /**
         * Compact constructor ensuring a non-null, unmodifiable list.
         *
         * @param reasonDetails the list of reason-detail objects.
         */
        public Failure {
            if (reasonDetails == null) {
                reasonDetails = Collections.emptyList();
            } else {
                reasonDetails = List.copyOf(reasonDetails);
            }
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        /**
         * Joins all the detail messages into a single string with a delimiter.
         *
         * @param delimiter The delimiter to use between each detail message.
         * @return A concatenated string of all reason-detail messages.
         */
        public String combinedDetails(String delimiter) {
            return reasonDetails.stream()
                    .map(ReasonDetail::detail)
                    .reduce((d1, d2) -> d1 + delimiter + d2)
                    .orElse("");
        }

        /**
         * Returns the first {@link ReasonDetail} if it exists, or {@code null} otherwise.
         *
         * @return The first reason-detail pair in the list, or {@code null}.
         */
        public ReasonDetail firstDetail() {
            return reasonDetails.isEmpty() ? null : reasonDetails.get(0);
        }
    }

    /**
     * Returns a successful result instance.
     *
     * @return A new instance of {@link Success}.
     */
    static PetSpawnResult success() {
        return new Success();
    }

    /**
     * Returns a failure result instance containing exactly one reason.
     *
     * @param reason The primary failure reason.
     * @param detail The message explaining the reason in detail.
     * @return A new instance of {@link Failure}.
     */
    static PetSpawnResult failure(FailureReason reason, String detail) {
        return new Failure(List.of(new ReasonDetail(reason, detail)));
    }

    /**
     * Returns a failure result instance containing multiple reason-detail pairs.
     *
     * @param reasonDetails A list of paired reasons and details.
     * @return A new instance of {@link Failure}.
     */
    static PetSpawnResult failure(List<ReasonDetail> reasonDetails) {
        return new Failure(reasonDetails);
    }

    /**
     * Represents a pairing of a {@link FailureReason} with a descriptive message.
     *
     * @param reason The specific failure reason.
     * @param detail A descriptive message explaining why it failed.
     */
    record ReasonDetail(FailureReason reason, String detail) {
    }
}
