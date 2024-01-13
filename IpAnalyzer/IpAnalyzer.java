public class IpAnalyzer {

    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("X-FORWARDED-FOR");
        //로드 벨런서나 프록시를 거치는 경우 사용되는 비표준 헤더
        if (ip == null) {
            ip = req.getRemoteAddr();
            //요청이 전달되는 최종 위치의 ip 주소 반환. 로드 벨런서나, 프록시를 거치는 경우 해당 지점이 최종 지점이므로 실제 클라이언트의 ip 주소가 아닐 수 있다
        }
        return ip;
    }
}
