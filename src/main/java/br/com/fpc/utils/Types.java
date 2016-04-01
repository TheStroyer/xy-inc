package br.com.fpc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fernando.costa
 *
 */
public enum Types {
	Inteiro(0) {
		@Override
		public String getTable() {
			return "int_values";
		}

		@SuppressWarnings("unchecked")
		@Override
		public Integer getValue(String value) {
			return Integer.parseInt(value);
		}

	},
	Decimal(1) {
		@Override
		public  String getTable() {
			return "double_values";
		}

		@SuppressWarnings("unchecked")
		@Override
		public Double getValue(String value) {
			return Double.valueOf(value);
		}

	},
	Palavra(2) {
		@Override
		public String getTable() {
			return "string_values";
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getValue(String value) {
			return value;
		}
	},
	Texto(3) {
		@Override
		public String getTable() {
			return "string_values";
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getValue(String value) {
			return value;
		}

	},
	Data(4) {
		@Override
		public String getTable() {
			return "datetime_values";
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getValue(String value)   {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
	        try {
	            Date date = simpleDateFormat.parse(value);
	            return simpleDateFormat.format(date);
	            
	        } catch (Exception ex) { }
			return "";
		}

	};

	private final int type;

	Types(int type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public abstract String getTable();
	
	/**
	 * @param value
	 * @return
	 * @throws NumberFormatException
	 */
	public abstract <T> T getValue(String value);

	/**
	 * @param type
	 * @return
	 */
	public static Types getFor(int type) {
		for (Types t : values()) {
			if (t.type == type) {
				return t;
			}
		}
		throw new IllegalArgumentException("Invalid type");
	}
	
	/**
	 * @return
	 */
	public int getType(){
		return type;
	}
}
