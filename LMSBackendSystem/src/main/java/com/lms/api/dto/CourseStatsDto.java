package com.lms.api.dto;


public class CourseStatsDto {

	//private Long id;
	private long numModules;
    private long numVideos;
    private String contentDuration;

	/*
	 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
	 * }
	 */
	public long getNumModules() {
		return numModules;
	}
	public void setNumModules(long numModules) {
		this.numModules = numModules;
	}
	public long getNumVideos() {
		return numVideos;
	}
	public void setNumVideos(long numVideos) {
		this.numVideos = numVideos;
	}
	public String getContentDuration() {
		return contentDuration;
	}
	public void setContentDuration(String contentDuration) {
		this.contentDuration = contentDuration;
	}
	
	
}
