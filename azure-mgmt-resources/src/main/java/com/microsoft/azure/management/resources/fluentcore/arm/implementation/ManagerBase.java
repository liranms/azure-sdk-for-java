/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.resources.fluentcore.arm.implementation;

import com.microsoft.azure.management.resources.implementation.ResourceManager;
import com.microsoft.azure.RestClient;

/**
 * Base class for Azure resource managers.
 */
public abstract class ManagerBase {

    private final ResourceManager resourceManager;

    protected ManagerBase(RestClient restClient, String subscriptionId) {
        this.resourceManager = ResourceManager.authenticate(restClient).withSubscription(subscriptionId);
    }

    /**
     * @return the {@ResourceManager} associated with this manager
     */
    public ResourceManager resourceManager() {
        return this.resourceManager;
    }
}
