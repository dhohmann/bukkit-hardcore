package io.github.dhohmann.hardcore;

public class VersionInfo {

	private int major = -1;
	private int minor = -1;
	private int patch = -1;
	private String identifier = null;
	private boolean snapshot = false;

	public VersionInfo(String version) {
		parse(version);
	}

	protected void parse(String version) {
		int index = 0;
		int length = version.length();
		StringBuilder substring = new StringBuilder();
		while (index < length) {
			char c = version.charAt(index);
			if (c == '.' && minor == -1) {
				if (major == -1) {
					major = Integer.parseInt(substring.toString());
					substring = new StringBuilder();
				} else if (minor == -1) {
					minor = Integer.parseInt(substring.toString());
					substring = new StringBuilder();
				}
				index++;
				continue;
			}
			if (c == '-') {
				if (patch == -1) {
					patch = Integer.parseInt(substring.toString());
					substring = new StringBuilder();
				} else if (identifier == null) {
					identifier = substring.toString();
					substring = new StringBuilder();
				}
				index++;
				continue;
			}
			substring.append(c);
			index++;
		}
		if (substring.length() == 0) {
			return;
		}
		if (substring.indexOf("SNAPSHOT") != -1) {
			snapshot = true;
		} else if (minor == -1) {
			minor = Integer.parseInt(substring.toString());
		} else if (patch == -1) {
			patch = Integer.parseInt(substring.toString());
		}

	}

	public boolean isSnapshot() {
		return snapshot;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	public boolean isAfter(String version) {
		VersionInfo v = new VersionInfo(version);
		if (v.major > major || v.minor > minor || v.patch > patch) {
			return true;
		}
		return false;
	}

	public boolean isBefore(String version) {
		VersionInfo v = new VersionInfo(version);
		if (v.major < major || v.minor < minor || v.patch < patch) {
			return true;
		}
		return false;
	}
	
	public boolean isEqualOrHigherThan(String version) {
		return isAfter(version) || isEqual(version);
	}

	public boolean isEqual(String version) {
		VersionInfo v = new VersionInfo(version);
		return v.equals(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VersionInfo) {
			VersionInfo v = (VersionInfo) obj;
			return v.major == major && v.minor == minor && v.patch == patch && v.snapshot == snapshot
					&& v.identifier.equals(identifier);
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Version ");
		builder.append(major);
		if (minor != -1) {
			builder.append(".").append(minor);
		}
		if (patch != -1) {
			builder.append(".").append(patch);
		}
		if (identifier != null) {
			builder.append("-").append(identifier);
		}
		if (snapshot) {
			builder.append("-").append("SNAPSHOT");
		}
		return builder.toString();
	}

}
