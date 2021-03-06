/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.core.internal.util.analytics;

import com.speedment.runtime.core.component.InfoComponent;
import com.speedment.runtime.core.internal.util.testing.TestSettings;

import static com.speedment.runtime.core.util.StaticClassUtil.instanceNotAllowed;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author pemi
 */
public final class AnalyticsUtil {

    private static final String TRACKING_CODE = "UA-54384165-1";

    public static void notify(final FocusPoint focusPoint, InfoComponent infoComponent) {
        requireNonNull(focusPoint);
        requireNonNull(infoComponent);
        if (!TestSettings.isTestMode()) {
            final JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker(TRACKING_CODE);
            tracker.setLoggingAdapter(new LoggingAdapterImpl());
            tracker.trackAsynchronously(focusPoint, infoComponent);
        }
    }

    /**
     * Utility classes should not be instantiated.
     */
    private AnalyticsUtil() {
        instanceNotAllowed(getClass());
    }
}
