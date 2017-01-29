package com.kamontat.server;

import com.kamontat.constant.RequestStatus;
import com.kamontat.exception.RequestException;
import com.kamontat.model.User;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

/**
 * @author kamontat
 * @version 2.1
 * @since 1/26/2017 AD - 6:11 PM
 */
public class GithubLoader {
	public enum Type {
		ANONYMOUS, AUTH;
	}
	
	private static Type type;
	private static GithubToken token;
	
	
	public static void setAuth(GithubToken token) {
		GithubLoader.type = Type.AUTH;
		GithubLoader.token = token;
	}
	
	public static void setAnonymous() {
		GithubLoader.type = Type.ANONYMOUS;
	}
	
	public static GitHub getGithub() throws RequestException {
		try {
			if (type == Type.AUTH) return GitHub.connectUsingOAuth(token.getToken());
			else return GitHub.connectAnonymously();
		} catch (IOException e) {
			throw new RequestException(RequestStatus.GITHUB_ERROR);
		}
	}
	
	public static GHRateLimit getRateLimit() throws RequestException {
		GHRateLimit rateLimit;
		try {
			rateLimit = getGithub().rateLimit();
		} catch (IOException e) {
			throw new RequestException(RequestStatus.INTERNET_ERROR);
		}
		return rateLimit;
	}
	
	public static GHUser getUser(String name) throws RequestException {
		try {
			return getGithub().getUser(name);
		} catch (IOException e) {
			throw new RequestException(RequestStatus.USER_NOT_FOUND);
		}
	}
	
	public static Map<String, GHRepository> getRepositories(User user) throws RequestException {
		try {
			return user.githubUser.getRepositories();
		} catch (IOException e) {
			throw new RequestException(e, user.fullname);
		}
	}
	
	public static int getMaximumRateLimit() {
		if (type == Type.ANONYMOUS) return 60;
		else if (type == Type.AUTH) return 5000;
		else return 0;
	}
}