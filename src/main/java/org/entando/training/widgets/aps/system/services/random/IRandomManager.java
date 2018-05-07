/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.training.widgets.aps.system.services.random;

import com.agiletec.aps.system.exception.ApsSystemException;


public interface IRandomManager {

	public Number getRandom() throws ApsSystemException;


}