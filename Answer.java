package br.com.verde.blue.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.verde.blue.util.DateUtils;

public class Answer implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
		result = prime * result + questionId;
		result = prime * result + searchNumber;
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
		Answer other = (Answer) obj;
		if (clientId != other.clientId)
			return false;
		if (questionId != other.questionId)
			return false;
		if (searchNumber != other.searchNumber)
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
	static final int ONE_DAY_IN_MILISECONDS = 86400000;
	public static final String NOT_APPLY_RESPONSE_TEXT = "2";

	private int searchNumber;
	private int clientId;
	private Date searchDate = new Date();
	private int questionId;
	private Double numericAnswer = 0.0;
	private String alphanumericAnswer = new String();
	private String answerLine = new String();
	private long lat = 0L;
	private long lng = 0L;
	private int channelId;
	private String timeAnswer;
	private String timeFillQuestions;
	private String comment;
	private String action;

	// TODO - constructor with required fields

	public Answer(String answerLine) {
		setAnswerLine(answerLine);
		setFieldsFromAnswerLine(answerLine);
	}

	public Answer() {
	}

	public void setFieldsFromAnswerLine(String answerLine) {
		try {
			setSearchNumber(getSearchNumberFromAnswerLine(answerLine));
			setClientId(getClientIdFromAnswerLine(answerLine));
			setSearchDate(getSearchDateFromAnswerLine(answerLine));
			setQuestionId(getQuestionIdFromAnswerLine(answerLine));
			setNumericAnswer(getNumericAnswerFromAnswerLine(answerLine));
			setAlphanumericAnswer(getAlphanumericAnswerFromAnswerLine(answerLine));

		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"error converting string to number");
		}
	}

	public void setAnswerLine(String answerLine) {
		if (answerLine == null) {
			throw new IllegalArgumentException("answerLine cannot be null");
		} else if (answerLine.length() != 81) {
			throw new IllegalArgumentException("answerLine length is "
					+ answerLine.length() + " but must be 81 ");
		}
		this.answerLine = answerLine;

	}

	public void setSearchNumber(int searchNumber) {
		if (searchNumber < 0) {
			throw new IllegalArgumentException(
					"The searchNumber cannot be less than 0");
		} else if (searchNumber > 99999999) {
			throw new IllegalArgumentException(
					"The searchnumber cannot be greather than 99999999");
		}
		this.searchNumber = searchNumber;
	}

	public int getSearchNumber() {
		return searchNumber;
	}

	public void setClientId(int clientId) {
		if (clientId < 0) {
			throw new IllegalArgumentException(
					"The clientId cannot be less than 0");
		} else if (clientId > 99999999) {
			throw new IllegalArgumentException(
					"The clientId cannot be greather than 99999999");
		}
		this.clientId = clientId;

	}

	public int getClientId() {
		return clientId;
	}

	public void setSearchDate(Date searchDate) {
		if (searchDate == null) {
			throw new IllegalArgumentException("the searchDate cannot be null");
		}
//		if (!isValidSearchDate(searchDate)) {
//			throw new IllegalArgumentException(
//					"the searchDate cannot be greather than today");
//		}
		this.searchDate = searchDate;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	private boolean isValidSearchDate(Date searchDate) {
		if (searchDate.getTime() > new Date().getTime()) {
			return false;
		}
		return true;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		if (questionId < 0) {
			throw new IllegalArgumentException(
					"The questionId cannot be less than 0");
		} else if (questionId > 99999999) {
			throw new IllegalArgumentException(
					"The questionId cannot be greather than 99999999");
		}
		this.questionId = questionId;
	}

	public Double getNumericAnswer() {
		if (numericAnswer == null) {
			numericAnswer = 0.0;
		}
		return numericAnswer;
	}

	public void setNumericAnswer(Double numericAnswer) {
		if (numericAnswer == null) {
			throw new IllegalArgumentException(
					"The numericAnswer cannot be null");
		}
		if (numericAnswer < 0) {
			throw new IllegalArgumentException(
					"The numericAnswer cannot be less than 0");
		} else if (numericAnswer > 9999999999999.999) {
			throw new IllegalArgumentException(
					"The numericAnswer cannot be greather than 99999999999999.999");
		}
		this.numericAnswer = numericAnswer;
	}

	public String getAlphanumericAnswer() {
		return alphanumericAnswer;
	}

	public void setAlphanumericAnswer(String alphanumericAnswer) {
		if (alphanumericAnswer == null) {
			throw new IllegalArgumentException(
					"the alphanumericAnswer cannot be null");
		}
		if (alphanumericAnswer.length() > 30) {
			throw new IllegalArgumentException(
					"the alphanumericAnswer cannot be bigger than 30");
		}
		this.alphanumericAnswer = alphanumericAnswer;
	}

	public static boolean isAnswerLineValid(String answerLine) {
		if (answerLine == null) {
			return false;
		}
		if (answerLine.length() == 81) {
			return true;
		}

		return false;
	}

	private int getSearchNumberFromAnswerLine(String answerLine) {
		String searchNumberString = answerLine.substring(0, 8);
		return Integer.parseInt(searchNumberString);
	}

	private int getClientIdFromAnswerLine(String answerLine) {
		String clientIdString = answerLine.substring(8, 16);
		return Integer.parseInt(clientIdString);
	}

	private Date getSearchDateFromAnswerLine(String answerLine) {
		String searchDateString = answerLine.substring(16, 26);
		return DateUtils.stringToDate(searchDateString);

	}

	private int getQuestionIdFromAnswerLine(String answerLine) {
		String questionIdString = answerLine.substring(26, 34);
		return Integer.parseInt(questionIdString);
	}

	private Double getNumericAnswerFromAnswerLine(String answerLine) {
		String numericAnswerString = answerLine.substring(34, 51).trim();
		return Double.parseDouble(addComa(numericAnswerString, 3));
	}

	private String getAlphanumericAnswerFromAnswerLine(String answerLine) {
		String alphanumericAnswerString = answerLine.substring(51, 81).trim();
		return alphanumericAnswerString;
	}

	public String getAnswerLine() {
		return answerLine;
	}

	@Override
	public String toString() {
		return getAnswerLine();
	}

	public String getLineToSave() {
		return formatIntegerToSave(getSearchNumber(), 8)
				+ formatIntegerToSave(getClientId(), 8)
				+ formatDateToSave(getSearchDate())
				+ formatIntegerToSave(getQuestionId(), 8)
				+ formatDoubleToSave(getNumericAnswer())
				+ formatStringToSave(getAlphanumericAnswer(), 30)
				+ formatStringToSave(String.valueOf(getLat()), 9)
				+ formatStringToSave(String.valueOf(getLng()), 9)
				+ formatIntegerToSave(getChannelId(), 3)
				+ formatTimeToSave(getSearchDate())
				+ formatStringToSave(getComment(), 50)
				+ formatStringToSave(getAction(), 50)
				+ formatStringToSave(getTimeAnswer(), 10)
				;
	}

	public String formatDateToSave(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
	
	public String formatTimeToSave(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	public String formatIntegerToSave(int number, int size) {
		String numberString = Integer.toString(number);
		while (numberString.length() < size)
			numberString = "0" + numberString;
		return numberString;
	}

	public String formatDoubleToSave(double number) {
		final Double numberDouble = new Double(number);

		String numberBeforePoint = new Integer(numberDouble.intValue())
				.toString();
		String numberAfterPoint = getNumberAfterPoint(numberDouble);

		while (numberBeforePoint.length() < 14) {
			numberBeforePoint = "0" + numberBeforePoint;
		}

		// System.out.println("before " + numberBeforePoint);
		// clientes respondidos n funciona
		while (numberAfterPoint.length() < 3) {
			numberAfterPoint = numberAfterPoint + "0";
		}
		// System.out.println("after " + numberAfterPoint);

		final String formatedString = numberBeforePoint + numberAfterPoint;
		// System.out.println("formated String " + formatedString);
		// System.out.println("length" + formatedString.length());

		return formatedString;

	}

	private String getNumberAfterPoint(Double numberDouble) {
		String numberAsString = numberDouble.toString();
		while (numberAsString.substring(0, 1).equals(".") == false) {
			numberAsString = numberAsString.substring(1);
		}
		numberAsString = numberAsString.substring(1);
		return numberAsString;
	}

	private String addComa(String numberString, int decimalsAmount) {
		return numberString
				.substring(0, numberString.length() - decimalsAmount)
				+ "."
				+ numberString
						.substring(numberString.length() - decimalsAmount);
	}

	public String formatStringToSave(String string, int size) {
		string = string == null ? "" : string;
		while (string.length() < size)
			string = string + " ";
		return string;
	}

	public static List<Integer> getAnsweredClientIdList(
			List<Answer> answers) {
		Set<Integer> clientIdSet = new HashSet<Integer>();
		for (Answer answer : answers) {
			clientIdSet.add(answer.clientId);
		}
		return new ArrayList<Integer>(clientIdSet);
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public long getLng() {
		return lng;
	}

	public void setLng(long lng) {
		this.lng = lng;
	}

	public String getTimeAnswer() {
		return timeAnswer;
	}

	public void setTimeAnswer(String timeAnswer) {
		this.timeAnswer = timeAnswer;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getTimeFillQuestions() {
		return timeFillQuestions;
	}
	
	public void setTimeFillQuestions(String timeFillQuestions) {
		this.timeFillQuestions = timeFillQuestions;
	}

}