package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.util.concurrent.Service;

interface ServiceManagerBridge {
    ImmutableMultimap<Service.State, Service> servicesByState();
}
