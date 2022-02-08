package com.gabryellr.gpstrack.information.adapter.outbound.batch.config;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class SkipPolicyConfig implements SkipPolicy {

    public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {
        return true;
    }
}
