package com.gandreani.lastpass;

public class Account {

	public String id, name, group, url, username, password;

	public Account(String id, String name, String group, String url,
			String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.group = group;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Account() {
	}

	public static Account getAccountFromChunk(ACCTChunk ac) {
		Account a = new Account();
		a.id = ac.items.get(0).data;
		a.name = ac.items.get(1).data;
		a.group = ac.items.get(2).data;
		a.url = ac.items.get(3).data;
		a.username = ac.items.get(7).data;
		a.password = ac.items.get(8).data;

		return a;
	}

	public boolean equals(Account other) {
		//@formatter:off //Eclipse formatting
		return     areEqual(id, other.id) 
				&& areEqual(name, other.name)
				&& areEqual(group, other.group) 
				&& areEqual(username, other.username)
				&& areEqual(password, other.password);
		//@formatter:on
	}

	private boolean areEqual(String s1, String s2) {
		return s1 == null ? s2 == null : s1.equals(s2);
	}

}
