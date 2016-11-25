package com.wrapper.spotify;

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * This class is needed to delete tracks from a playlist. 
 * It's very hackish but needed because apache does not support a requestBody
 * when using a normal DeleteMethod.
 * TODO: Find a better solution.
 */
public class MyDeleteMethod extends PostMethod {

	public MyDeleteMethod() {
		super();
	}

	public MyDeleteMethod(String uri) {
		super(uri);
	}

	@Override
	public String getName() {
		// TODO(Ich-Eben): Very ugly
		return "DELETE";
	}
	
}
