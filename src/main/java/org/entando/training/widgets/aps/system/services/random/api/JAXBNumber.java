/*
*
* <Your licensing text here>
*
*/
package org.entando.training.widgets.aps.system.services.random.api;

import org.entando.training.widgets.aps.system.services.random.Number;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.entando.training.widgets.aps.system.services.random.Number;

@XmlRootElement(name = "random")
@XmlType(propOrder = {"id", "output"})
public class JAXBNumber {
    
    public JAXBNumber() {
        super();
    }
    
    public JAXBNumber(Number random) {
        this.setOutput(random.getOutput());
    }
    
    public Number getNumber() {
        Number number = new Number();
        
        number.setId(this.getId());
        number.setOutput(this.getOutput());
        return number;
    }
    
    @XmlElement(name = "id", required = false)
    public Integer getId() {
        return _id;
    }
    public void setId(Integer id) {
        this._id = id;
    }
    
    @XmlElement(name = "output", required = true)
    public Integer getOutput() {
        return _output;
    }
    public void setOutput(Integer output) {
        this._output = output;
    }
    
    
    private Integer _id;
    private Integer _output;
    
}
