package com.musicyao.util;

import java.io.File;
import java.io.FileFilter;

import org.jaudiotagger.audio.generic.Utils;

import com.musicyao.enumeration.SupportedFileFormat;

public class AudioFileFilter implements FileFilter{
	
	private final boolean allowDirectories;
	
	public AudioFileFilter( boolean allowDirectories)
    {
        this.allowDirectories=allowDirectories;
    }
	
	public AudioFileFilter() {
		this(true);
	}
	
	@Override
	public boolean accept(File f) {
		if (f.isHidden() || !f.canRead()) {
            return false;
        }
		if (f.isDirectory()) {
            return allowDirectories;
        }
		String ext = Utils.getExtension(f);
		try {
			if(SupportedFileFormat.valueOf(ext.toUpperCase())!=null) {
				return true;
			}
		} catch(IllegalArgumentException iae) {
			return false;
		}
		return false;
	}

}
