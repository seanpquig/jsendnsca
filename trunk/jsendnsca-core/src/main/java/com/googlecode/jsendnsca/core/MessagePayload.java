/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.jsendnsca.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import com.googlecode.jsendnsca.core.utils.StringUtils;

/**
 * The Passive Check Message Payload
 * 
 * @author Raj.Patel
 * @version 1.0
 */
public class MessagePayload {

	/**
	 * OK Level
	 */
	public static final int LEVEL_OK = 0;
	/**
	 * Warning level
	 */
	public static final int LEVEL_WARNING = 1;
	/**
	 * Critical Level
	 */
	public static final int LEVEL_CRITICAL = 2;
	/**
	 * Unknown level
	 */
	public static final int LEVEL_UNKNOWN = 3;

	private static final String DEFAULT_HOSTNAME = "localhost";
	private static final String DEFAULT_SERVICENAME = "UNDEFINED";

	private String hostname = DEFAULT_HOSTNAME;
	private int level = LEVEL_UNKNOWN;
	private String serviceName = DEFAULT_SERVICENAME;
	private String message = StringUtils.EMPTY;

	/**
	 * Construct a new {@link MessagePayload}
	 */
	public MessagePayload() {

	}

	/**
	 * Construct a new {@link MessagePayload}
	 * 
	 * @param hostname
	 *            the hostname to be sent in this passive check
	 * @param level
	 *            the level
	 * @param serviceName
	 *            the service name
	 * @param message
	 *            the message
	 */
	public MessagePayload(String hostname, int level, String serviceName,
			String message) {
		if (StringUtils.isBlank(hostname) || StringUtils.isBlank(serviceName)) {
			throw new IllegalArgumentException(
					"hostname or serviceName cannot be null or an empty String");
		}

		this.hostname = hostname;
		this.level = level;
		this.serviceName = serviceName;
		this.message = message;
	}

	/**
	 * The hostname to be sent in this passive check
	 * 
	 * @return the hostname, defaults to "localhost"
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * Use the short hostname of this machine in the passive check
	 * 
	 * @throws UnknownHostException
	 *             thrown if unable to determine the machines hostname
	 */
	public void useLocalHostname() throws UnknownHostException {
		setHostname(false);
	}

	/**
	 * Set the hostname in the passive check
	 * 
	 * @param useCanonical
	 *            true to use this machines fully qualified domain name, false
	 *            to use the short hostname
	 * @throws UnknownHostException
	 *             thrown if unable to determine the machines hostname
	 */
	public void setHostname(boolean useCanonical) throws UnknownHostException {
		InetAddress ipAddress = InetAddress.getLocalHost();
		if (useCanonical) {
			this.hostname = ipAddress.getCanonicalHostName();
		} else {
			this.hostname = ipAddress.getHostName();
		}
	}

	/**
	 * Set the hostname in the passive check
	 * 
	 * @param hostname
	 *            the hostname to use
	 */
	public void setHostname(String hostname) {
		if (StringUtils.isBlank(hostname)) {
			throw new IllegalArgumentException(
					"hostname cannot be null or an empty String");
		}
		this.hostname = hostname;
	}

	/**
	 * Get the level of the Passive check
	 * 
	 * @return the level, default is UNKNOWN
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Set the level of the Passive check
	 * 
	 * @param level
	 *            the level, use the constant field values LEVEL_...
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Set the level of the Passive check using a {@link String} The case of the
	 * {@link String} is ignored
	 * 
	 * @param level
	 *            either "ok", "warning", "critical" or "unknown"
	 */
	public void setLevel(String level) {
		if (StringUtils.isBlank(level)) {
			throw new IllegalArgumentException(
					"Level cannot be null or an empty String");
		}
		
		level = level.toLowerCase();

		if (level.equals("ok")) {
			this.level = LEVEL_OK;
		} else if (level.equals("warning")) {
			this.level = LEVEL_WARNING;
		} else if (level.equals("critical")) {
			this.level = LEVEL_CRITICAL;
		} else if (level.equals("unknown")) {
			this.level = LEVEL_UNKNOWN;
		} else {
			throw new IllegalArgumentException(MessageFormat.format(
					"Level [{0}] is not recognised", level));
		}

	}

	/**
	 * The service name of this passive check
	 * 
	 * @return the service name, default is "UNDEFINED"
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Set the service name of this passive check
	 * 
	 * @param serviceName
	 *            the service name
	 */
	public void setServiceName(String serviceName) {
		if (StringUtils.isBlank(serviceName)) {
			throw new IllegalArgumentException(
					"serviceName cannot be null or an empty String");
		}
		this.serviceName = serviceName;
	}

	/**
	 * The message to send in this passive check
	 * 
	 * @return the message, default is an empty string
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message to send in this passive check
	 * 
	 * @param message
	 *            the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}