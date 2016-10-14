package net.spy.memcached.util;

import net.spy.memcached.MemcachedNode;

/**
 * An implementation of the configuration required for the KetamaNodeLocator algorithm
 * to run using the DNS name and port of the node to calculate the node's key.
 */
public class KetamaDNSNameNodeLocatorConfiguration extends DefaultKetamaNodeLocatorConfiguration {

    /**
     * Returns the address of a given MemcachedNode using its DNS name and port.
     *
     * @param node The node which we're interested in
     * @return String the address of that node.
     */
    @Override
    protected String getAddressForNode(MemcachedNode node) {
        String result = address.get(node);
        if (result == null) {
            result = String.valueOf(node.getHostPort().hashCode());
            if (result.startsWith("/")) {
                result = result.substring(1);
            }
            address.put(node, result);
        }
        return result;
    }

}