package com.lms.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.CourseStatsDto;
import com.lms.api.dto.ModuleDto;
import com.lms.api.model.Course;
import com.lms.api.model.Module;
import com.lms.api.model.Video;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.ModuleRepository;
import com.lms.api.repository.VideoRepository;

@RestController
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	/*
	 * module post API
	 */
	
	@PostMapping("/module/{cid}")
	public Module postModule(@RequestBody Module module,@PathVariable("cid") Long cid) {
		Course course=courseRepository.getById(cid);
		module.setCourse(course);
		return moduleRepository.save(module);
	}
	
	/*
	 * Video post API
	 */
	@PostMapping("/video/{mid}")
	public Video postVideo(@RequestBody Video video,@PathVariable("mid") Long mid) {
		/*
		 *  fetch all modules based on moduleId
		 */
		Module module=moduleRepository.getById(mid);
		video.setModule(module);
		return videoRepository.save(video);
	}
	
	
	/*
	 * Get all Modules by CourseId 
	 * Along with module, give video info as well
	 * [ {
	 *    id:
	 *    name: ,
	 *    sequence: , 
	 *    videos:[
	 *       {
	 *          id:
	 *          title:
	 *          duration: 
	 *       },
	 *       {
	 *    	 
	 *       } 
	 *    ]
	 * 
	 * }, 
	 * {
	 *    id:
	 *    name: ,
	 *    sequence: , 
	 *    videos:[
	 *       {
	 *          id:
	 *          title:
	 *          duration: 
	 *       },
	 *       {
	 *    	 
	 *       } 
	 *    ]
	 * 
	 * }
	 * ]
	 */
	@GetMapping("/module/{cid}")
	public List<ModuleDto> getAllModulesByCourseId(@PathVariable("cid") Long cid) {
		/*
		 * fetch all modules based on courseId
		 */
		List<Module> moduleList=moduleRepository.getByCourseId(cid);
		List<ModuleDto> dtoList=new ArrayList<>();
		
		for(Module m:moduleList) {
			ModuleDto dto=new ModuleDto();
			List<Video> listVideo=videoRepository.findByModuleId(m.getId());
			dto.setId(m.getId());
			dto.setName(m.getName());
			dto.setSequence(m.getSequence());
			dto.setVideoList(listVideo);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	/*
	 * Line no 107: List<Video> listVideo=videoRepository.findByModuleId(m.getId());
	 * we should avoid this because it includes too many DB calls so it might affects the performance of API.
	 * Means in this example for every video it goes to db and fetch records for us ..if we have 100 records it 
	 * goes 100 times to DB and fetch records for us.
	 * Use alternate way mentioned below...
	 */
	
	@GetMapping("/module/alternate/{cid}")
	public List<ModuleDto> getAllModulesByCourseIdAlternate(@PathVariable("cid") Long cid) {
		/*
		 * Step1: fetch all modules based on courseId
		 */
		List<Module> list=moduleRepository.getByCourseId(cid);
		List<ModuleDto> listDto=new ArrayList<>();
		
		/*
		 * fetch all videos for given courseId
		 */
		List<Video> listVideos=videoRepository.getByCourseId(cid);
		
		list.stream().forEach(m->{
			ModuleDto dto=new ModuleDto();
			//fetch videos for each moduleId
			List<Video> listVideo=listVideos.parallelStream()
					                 .filter(v->v.getModule().getId().equals(m.getId()))
					                 .collect(Collectors.toList());
			
			dto.setId(m.getId());
			dto.setName(m.getName());
			dto.setSequence(m.getSequence());
			dto.setVideoList(listVideo);
			listDto.add(dto);
		});
		
		return listDto;
	}
	
	/*
	 * {
	 *     numModules:
	 *     numVideos:
	 *     contentDuration: <display in hours and minutes> -136 minutes(2hrs, 16 mins)
	 * }
	 * 
	 */
	/*
	@GetMapping("/course/video/stats/{cid}")
	public CourseStatsDto courseStatsByVideo(@PathVariable("cid") Long cid) {
		
		 //Step1: fetch all modules based on courseId
		 
		List<Module> listModules=moduleRepository.getByCourseId(cid);
		
		 //Step2: fetch all videos based on courseId

		List<Video> listVideos=videoRepository.getByCourseId(cid);
	
		
		 // Step3: calculate total Duration Videos
		 
		
		List<String> durationList=listVideos.stream().map(v->v.getDuration()).collect(Collectors.toList());
		System.out.println(durationList); //[8.30,7.30,10.30,5.30]
		
		int totalHours=0;
		int totalMinutes=0;
		int totalSeconds=0;
		
		 // String s1="10.5";  19/60: 0 hrs
		 // String s2="8.5";   19%60: 19 mins
		 
		
		for(String d: durationList) { //8.30
			totalMinutes=totalMinutes+ Integer.parseInt(d.split("\\.")[0]); // 8+7+10+5=30
			totalSeconds=totalSeconds+ Integer.parseInt(d.split("\\.")[1]);//  30+30+30+30=120
			
		}
		totalMinutes=totalMinutes+(totalSeconds/60); //30+2=32
		totalSeconds=totalSeconds%60;  //120%60=0
		
		totalHours=totalMinutes/60; //32/60=0
		totalMinutes=totalMinutes%60;
		
		
		 // Step4: set values in Dto
		 
		CourseStatsDto dto=new CourseStatsDto();
		if(listModules !=null)
			dto.setNumModules(listModules.size());
		
		if(listVideos !=null)
			dto.setNumVideos(listVideos.size());
		
		dto.setContentDuration(totalHours + "hrs " + totalMinutes + "mins " + totalSeconds+ " secs");
		
		return dto;
	}*/
	@GetMapping("/course/video/stats/{cid}")
	public CourseStatsDto courseStatsByVideo(@PathVariable("cid")Long cid) {
		
		/*
		 * fETCH ALL MODULES BASED ON COURSEID
		 */
		List<Module> listModules= moduleRepository.getByCourseId(cid);
		
		/*
		 * FETCH ALL VIDEOES BASED ON COURSEID
		 */
		 List<Video> listVideos= videoRepository.getByCourseId(cid);
		//List<ModuleDto> listDto= new ArrayList<>();
		
		 CourseStatsDto dto= new  CourseStatsDto();
		
	//	dto.setNumModules(listModules.size());
	//	dto.setNumVideos(listVideos.size());
		//dto.setNumVideos(dto.get)
		
		 /*
		  * CALCULATE TOTAL DURATION O VIDEOS
		  */
		List<String> durationList= listVideos.stream()
				                   .map(v->v.getDuration())
				                   .collect(Collectors.toList());
		
		//System.out.println(durationList);
		//double sum=0;
		int totalHours=0;
		int totalMins=0;
		int totalSeconds=0;
		/*
		 * String s1="10.5" //10.5 =19/60: 0 hrs
		 * String s2="8.5" = 19% 60 : 19 mins		 
		 */
		for(String d: durationList){
			
			totalMins=totalMins + Integer.parseInt(d.split("\\.")[0]); //8+7+10+5=30
			totalSeconds=totalSeconds + Integer.parseInt(d.split("\\.")[1]); //30+30+30+30 =120
			//double xd=Double.parseDouble(d);
		   // sum= sum+xd;
		}
		
		 totalMins=totalMins+(totalSeconds / 60); // 30+2=32
		 totalSeconds=totalSeconds % 60; //120%60=0
		 
		 
		 totalHours=totalMins / 60; // 32/60=0
		 totalMins=totalMins % 60;
		
		//CourseStasDto dto= new CourseStasDto();
		if(listModules!=null)
			 dto.setNumModules(listModules.size());
		if(listVideos!=null)
			dto.setNumVideos(listVideos.size());
		
		dto.setContentDuration(totalHours+ "hrs "+ totalMins +"mins " + totalSeconds + "sec ");
			return dto;
		
	}
	
	
}
