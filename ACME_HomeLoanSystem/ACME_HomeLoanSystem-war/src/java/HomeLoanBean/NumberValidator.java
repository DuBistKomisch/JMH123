/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeLoanBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author Howard Tseng
 */
@FacesValidator("numberValidator")
public class NumberValidator implements Validator{
    
   
        
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
    }
    
}
