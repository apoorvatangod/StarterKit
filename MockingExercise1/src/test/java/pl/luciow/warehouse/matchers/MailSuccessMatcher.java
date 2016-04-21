package pl.luciow.warehouse.matchers;

import org.mockito.ArgumentMatcher;

import pl.luciow.warehouse.model.Mail;

public class MailSuccessMatcher extends ArgumentMatcher<Mail>{

	@Override
	public boolean matches(Object argument) {
		return "Success".equals(((Mail) argument).getContent());
	}
}
