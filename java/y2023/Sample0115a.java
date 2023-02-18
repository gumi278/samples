package y2023;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Sample0115a
{
	/**
	 * 日時を表す文字列とUNIX時間(long)のサンプルコード
	 *
	 * 厳密ではないけれどタイムゾーンを意識した日時の数値とテキストの変換を行うサンプル。
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)
		throws Exception
	{
		//対象となる年月日と時分秒とミリ秒
		final String DATE_TIME = "2023-01-12,07-04-48,975";
		
		//対象の地域
		final ZoneId DATE_TIME_ZONE = ZoneId.of("Asia/Tokyo");
		
		//対象の形式
		final String DATE_TIME_PATTERN = "yyyy-MM-dd,HH-mm-ss,SSS";
		
		//処理の流れ
		//テキスト形式の日時が最初にあったとして
		//１）どの地域での日時だったか(タイムゾーン指定)： String
		//２）日時の形式： String
		//の２つを使って、UNIX時間のミリ秒単位のカウント値を得る。これが基準の値になる。
		//
		//UNIX時間からテキスト形式に変換する際に、どの地域に向けて時間軸を変化させるか指定することになる。
		//同じUNIX時間の瞬間、地球上ではどこかでは朝、どこかでは昼、どこかでは夜。
		//
		//UNIX時間からテキストへの変換は
		//１）地域： String
		//２）形式： String
		//の２つを使ってテキストに変換することになる。
		//
		//この方式は閏秒をなんとなく扱う。なので完全な時間管理ではない。そしてサマータイムも考慮していない。
		//実際はわずかに長かったり短かったりするわけだけど、きっかりした数値（UNIX時間）なので
		//数値だけみてなんとなくわかった感じになれる。手持ちが何もない状態ではそれだけでありがたい。
		
		long time = 0L;
		String s1 = null;
		
		System.out.print("original: ");
		System.out.println(DATE_TIME);
		
		//日時のテキストから、地域と形式の２つを使ってUNIX時間を得る
		time = sample1(DATE_TIME, DATE_TIME_ZONE, DATE_TIME_PATTERN);
		System.out.print("time: ");
		System.out.println(time);
		
		//UNIX時間から、地域と形式の２つを使って日時テキストを得る
		//元と同じ地域を指定した場合、元と同じ日時テキストになるはず。
		s1 = sample2(time, DATE_TIME_ZONE, DATE_TIME_PATTERN);
		System.out.print("[Asia/Tokyo]: ");
		System.out.println(s1);
		
		//同じUNIX時間の瞬間、パリでは何時何分だったのか
		s1 = sample2(time, ZoneId.of("Europe/Paris"), DATE_TIME_PATTERN);
		System.out.print("[Europe/Paris]: ");
		System.out.println(s1);
		
		return;
	}
	
	
	static long sample1(final String date_time,final ZoneId date_time_zone,final String date_time_pattern)
		throws ParseException
	{
		long result = 0L;
		final SimpleDateFormat format = new SimpleDateFormat(date_time_pattern);
		final TimeZone zone = TimeZone.getTimeZone(date_time_zone);
		Date d1 = null;
		
		format.setTimeZone(zone);
		d1 = format.parse(date_time);
		result = d1.getTime();
		
		return result;
	}
	
	
	static String sample2(final long date_time,final ZoneId date_time_zone,final String date_time_pattern)
	{
		String result = null;
		final SimpleDateFormat format = new SimpleDateFormat(date_time_pattern);
		final TimeZone zone = TimeZone.getTimeZone(date_time_zone);
		final Date d1 = new Date(date_time);
		
		format.setTimeZone(zone);
		result = format.format(d1);
		
		return result;
	}
}
