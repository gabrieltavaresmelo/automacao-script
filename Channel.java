package br.com.verde.blue.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Channel implements Serializable {

	private static final long serialVersionUID = 1L;
	private int channelId;
	private List<Question> rootQuestions = new ArrayList<Question>();
	private int terminalQuestionCount = 0;

	public Channel(int channelId) {
		setChannelId(channelId);
	}

	public void setChannelId(int channelId) {
		if (channelId < 0) {
			throw new IllegalArgumentException("channelId cannot be negative");
		} else if (channelId > 999) {
			throw new IllegalArgumentException(
					"channelId cannot be more than 999");
		}
		this.channelId = channelId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void addRootQuestion(Question rootQuestion) {
		if (rootQuestion == null) {
			throw new IllegalArgumentException("rootQuestion cannot be null");
		} else if (!rootQuestion.isRootQuestion()) {
			throw new IllegalArgumentException(
					"rootQuestion must be a root Question");
		} else if (rootQuestion.getChannelId() != getChannelId()) {
			throw new IllegalArgumentException(
					"rootQuestion channelId must match with this channelId");
		}
		rootQuestions.add(rootQuestion);
	}

	public List<Question> getRootQuestions() {
		return rootQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channelId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (channelId != other.channelId)
			return false;
		return true;
	}

	public int getTerminalQuestionCount() {
		return terminalQuestionCount;
	}

	public void setTerminalQuestionCount(int terminalQuestionCount) {
		if (terminalQuestionCount < 0) {
			throw new IllegalArgumentException(
					"terminalQuestionCount must be a natural integer");
		}
		this.terminalQuestionCount = terminalQuestionCount;
	}
}