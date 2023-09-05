package com.jointAccount;

import com.client.PhysicalClient;

public interface JointAccount
{
    public void includeOwner(PhysicalClient owner) throws Exception;
}
