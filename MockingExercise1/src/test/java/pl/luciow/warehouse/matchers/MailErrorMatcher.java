package pl.luciow.warehouse.matchers;

import org.mockito.ArgumentMatcher;

import pl.luciow.warehouse.model.Mail;

public class MailErrorMatcher extends ArgumentMatcher<Mail>{

	@Override
	public boolean matches(Object argument) {
		return "Error occured".equals(((Mail) argument).getContent());
	}
}
