/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * $Id: XPathFilterParameterSpec.java,v 1.4 2005/05/10 16:40:17 mullan Exp $
 */
package javax.xml.crypto.dsig.spec;

import javax.xml.crypto.dsig.Transform;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Parameters for the <a href="http://www.w3.org/TR/xmldsig-core/#sec-XPath">
 * XPath Filtering Transform Algorithm</a>.
 * The parameters include the XPath expression and an optional <code>Map</code> 
 * of additional namespace prefix mappings. The XML Schema Definition of
 * the XPath Filtering transform parameters is defined as:
 * <pre><code>
 * &lt;element name="XPath" type="string"/&gt;
 * </code></pre>
 *
 * @author Sean Mullan
 * @author JSR 105 Expert Group
 * @since 1.6
 * @see Transform
 */
public final class XPathFilterParameterSpec implements TransformParameterSpec {

    private String xPath;
    private Map nsMap;

    /**
     * Creates an <code>XPathFilterParameterSpec</code> with the specified 
     * XPath expression.
     *
     * @param xPath the XPath expression to be evaluated
     * @throws NullPointerException if <code>xPath</code> is <code>null</code>
     */
    public XPathFilterParameterSpec(String xPath) {
	if (xPath == null) {
	    throw new NullPointerException();
	}
	this.xPath = xPath;
	this.nsMap = Collections.EMPTY_MAP;
    }

    /**
     * Creates an <code>XPathFilterParameterSpec</code> with the specified 
     * XPath expression and namespace map. The map is copied to protect against
     * subsequent modification.
     *
     * @param xPath the XPath expression to be evaluated
     * @param namespaceMap the map of namespace prefixes. Each key is a
     *    namespace prefix <code>String</code> that maps to a corresponding
     *    namespace URI <code>String</code>.
     * @throws NullPointerException if <code>xPath</code> or
     *    <code>namespaceMap</code> are <code>null</code>
     * @throws ClassCastException if any of the map's keys or entries are not
     *    of type <code>String</code>
     */
    public XPathFilterParameterSpec(String xPath, Map namespaceMap) {
        if (xPath == null || namespaceMap == null) {
            throw new NullPointerException();
        }
        this.xPath = xPath;
	nsMap = new HashMap(namespaceMap);
	Iterator entries = nsMap.entrySet().iterator();
	while (entries.hasNext()) {
	    Map.Entry me = (Map.Entry) entries.next();
	    if (!(me.getKey() instanceof String) || 
		!(me.getValue() instanceof String)) {
		throw new ClassCastException("not a String");
	    }
	}
	nsMap = Collections.unmodifiableMap(nsMap);
    }

    /**
     * Returns the XPath expression to be evaluated.
     *
     * @return the XPath expression to be evaluated
     */
    public String getXPath() {
	return xPath;
    }

    /**
     * Returns a map of namespace prefixes. Each key is a namespace prefix 
     * <code>String</code> that maps to a corresponding namespace URI 
     * <code>String</code>.
     * <p>
     * This implementation returns an {@link Collections#unmodifiableMap 
     * unmodifiable map}.
     *
     * @return a <code>Map</code> of namespace prefixes to namespace URIs (may 
     *    be empty, but never <code>null</code>)
     */
    public Map getNamespaceMap() {
	return nsMap;
    }
}
