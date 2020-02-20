package danbooru;

public class DanbooruRequestBuilder {
	private static String fetchURL = "https://danbooru.donmai.us/posts.json?tags={$tag}&page={$page}";
	
	public static String getURL(String tag, int imgNum) {
		String result = fetchURL;
		result = result.replaceFirst("\\{\\$tag\\}", tag);
		
		int pageNum = imgNum / 20;
		result = result.replaceFirst("\\{\\$page\\}", pageNum + "");
		return result;
	}

}
