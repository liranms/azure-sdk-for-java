package com.microsoft.azure.management.resources;

import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import org.junit.Assert;
import org.junit.Test;

public class ResourceGroupsTests extends ResourceManagerTestBase {
    private  ResourceGroups resourceGroups;

    public ResourceGroupsTests() throws Exception {
        resourceGroups = resourceClient.resourceGroups();
    }

    @Test
    public void canCreateResourceGroup() throws Exception {
        String rgName = "javacsmrg2";
        String location = "southcentralus";
        // Create
        resourceGroups.define(rgName)
                .withLocation(Region.US_SOUTH_CENTRAL)
                .withTag("department", "finance")
                .withTag("tagname", "tagvalue")
                .create();
        // List
        ResourceGroup groupResult = null;
        for (ResourceGroup rg : resourceGroups.list()) {
            if (rg.name().equals(rgName)) {
                groupResult = rg;
                break;
            }
        }
        Assert.assertNotNull(groupResult);
        Assert.assertEquals("finance", groupResult.tags().get("department"));
        Assert.assertEquals("tagvalue", groupResult.tags().get("tagname"));
        Assert.assertEquals(location, groupResult.location());
        // Get
        ResourceGroup getGroup = resourceGroups.get(rgName);
        Assert.assertNotNull(getGroup);
        Assert.assertEquals(rgName, getGroup.name());
        Assert.assertEquals(location, getGroup.location());
        // Delete
        resourceGroups.delete(rgName);
        Assert.assertFalse(resourceGroups.checkExistence(rgName));
    }
}