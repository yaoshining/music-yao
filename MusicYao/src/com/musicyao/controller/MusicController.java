package com.musicyao.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.musicyao.bean.MusicInitCounter;
import com.musicyao.po.Music;
import com.musicyao.service.MusicService;
import com.musicyao.util.AudioFileUtil;
@Path("/music")
public class MusicController {
	@EJB
	private MusicService musicService;
	@Inject 
	private MusicInitCounter counter;
	@GET
	@Path("/{id}")
	public String test(@PathParam("id") String id){
		return "ok";
	}
	@POST
	@Path("/init")
	public int initMusics(String directory) {
		System.out.println(directory);
		return musicService.initMusics(directory);
	}
	@GET
	@Path("/init/status")
	public long initStatus(){
		return counter.getFinished();
	}
	@GET
	@Path("/random/{size}")
	@Produces({"application/json"})
	public List<Music> radom(@PathParam("size") int size){
		return musicService.random(size);
	}
	@GET
	@Path("/{id}.mp3")
	@Produces({"audio/mpeg"})
	public Response mp3(@PathParam("id") int id){
		Music mp3 = musicService.find(Music.class, id);
		final File mp3File = new File(mp3.getPath());
		ResponseBuilder response = Response.ok(mp3File);
		response.header("Content-Disposition",
				"attachment; filename="+mp3File.getName());
		response.header("Accept-Ranges", "bytes");
		FileInputStream in = null;
		try {
			in = new FileInputStream(mp3File);
			response.header("Content-Length", in.available());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response.build();
	}
	@GET
	@Path("/poster/{id}")
	@Produces({"image/png"})
	public byte[] poster(@PathParam("id") int id){
		Music mp3 = musicService.find(Music.class, id);
		final File mp3File = new File(mp3.getPath());
		byte[] poster = AudioFileUtil.getPoster(mp3File);
		return poster;
	}
}
