package org.bsdevelopment.simplepets.api.spawn;

/**
 * Describes possible reasons why a pet spawn might fail.
 */
public enum FailureReason {
    /**
     * The location is invalid, blocked, or denied for spawning.
     */
    LOCATION_DENIED,

    /**
     * A plugin or server event canceled the spawn attempt.
     */
    EVENT_CANCELED,

    /**
     * The player lacked the required permissions to spawn a pet.
     */
    NO_PERMISSION,

    /**
     * The pet is not available on this version.
     */
    UNAVAILABLE,

    /**
     * Unknown or unexpected reasons.
     */
    UNKNOWN
}
