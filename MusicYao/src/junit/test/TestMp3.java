package junit.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.reference.PictureTypes;
import org.junit.Test;

import com.musicyao.util.AudioFileUtil;
import com.sun.imageio.plugins.common.ImageUtil;

public class TestMp3 {
	@Test
	public void testMp3Header() {
		File testFile = new File("E:/Music/英语/A1/Make It Good/Do You Remember.mp3");
		try {
			MP3File f = (MP3File) AudioFileIO.read(testFile);
			MP3AudioHeader audioHeader = f.getMP3AudioHeader();
			int trackLength = audioHeader.getTrackLength();
			System.out.println(trackLength);
			audioHeader.getSampleRateAsNumber();
			audioHeader.getChannels();
			audioHeader.isVariableBitRate();
			audioHeader.getTrackLengthAsString();
			audioHeader.getMpegVersion();
			audioHeader.getMpegLayer();
			audioHeader.isOriginal();
			audioHeader.isCopyrighted();
			audioHeader.isPrivate();
			audioHeader.isProtected();
			audioHeader.getBitRate();
			audioHeader.getEncodingType();

		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testMp3Tag() {
		File testFile = new File("E:\\Music\\英语\\Akon\\Trouble\\Mr.Lonely(hip hop remix).mp3");
		try {
			AudioFile f = (MP3File) AudioFileIO.read(testFile);
			Tag tag = f.getTag();
			AudioHeader audioHeader = f.getAudioHeader();
			String artist = tag.getFirst(FieldKey.ARTIST);
			String album = tag.getFirst(FieldKey.ALBUM);
			String title = tag.getFirst(FieldKey.TITLE);
			String comment = tag.getFirst(FieldKey.COMMENT);
			String year = tag.getFirst(FieldKey.YEAR);
			String track = tag.getFirst(FieldKey.TRACK);
			String discNo = tag.getFirst(FieldKey.DISC_NO);
			String composer = tag.getFirst(FieldKey.COMPOSER);
			String artistSort = tag.getFirst(FieldKey.ARTIST_SORT);
			Artwork artwork = tag.getFirstArtwork();
			ImageIcon image = new ImageIcon(artwork.getBinaryData());
			JLabel label = new JLabel(image);
			JFrame frame = new JFrame();
			frame.setVisible(true);
			frame.setResizable(true);
			frame.setLayout(null);
			frame.setBounds(100, 100, 500, 500);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel p1 = (JPanel)frame.getContentPane();
			p1.setOpaque(false);  
			p1.setLayout(null);  
			frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
			label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
			System.out.println(artwork.getImage());
			frame.validate();
			System.out.println();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	class A {
		String str1="Hello!\t";
		String str2 = "How are you?";
		@Override
		public String toString() {
			return str1+str2;
		}
	}
	class B extends A{
		String str1="\b\b,Bill";
		@Override
		public String toString() {
			return super.str1+str2;
		}
	}
	@Test
	public void test() {
		double a3 = 5300;
		System.out.println(a3);
	}
}
