@Slf4j
public class IpInspectInterceptor implements HandlerInterceptor {
    //요청에 대해 Ip 대역을 검사하는 인터셉터 구현
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpAnalyzer.getClientIp(request);
        //요청자의 ip 주소를 String으로 불러온다
        log.info("Incoming IP: {}", ip);

       String ipv4Pattern = "^((25[0-5]|2[0-4]\\d|[0-1]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[0-1]?\\d\\d?)$";
        String ipv6Pattern = "^(?:[\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$";
        //정규식을 활용하여 IpV4, IpV6 구분 수행
        //간결한(concise) 표현식을 사용 - \\d, \\w
        String[] ipAddressParts = new String[0];
        if (ip.matches(ipv4Pattern)) {
            log.info("IPv4 filtering");
            ipAddressParts = ip.split("\\.");
            return (Integer.parseInt(ipAddressParts[0]) <= 192) &&
                    (Integer.parseInt(ipAddressParts[1]) <= 255) &&
                    (Integer.parseInt(ipAddressParts[2]) <= 255) &&
                    (Integer.parseInt(ipAddressParts[3]) <= 255);
        } else if (ip.matches(ipv6Pattern)) {
            log.info("IPv6 filtering");
            //ipV6의 경우 각 파트가 16진수로 구성되어 있으므로 이를 알기 편하게 10진수로 변경하여 검사
            return (Integer.parseInt((ipAddressParts[0]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[1]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[2]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[3]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[4]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[5]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[6]), 16) <= 65535) &&
                    (Integer.parseInt((ipAddressParts[7]), 16) <= 65535);
        } else {
            log.info("Invalid IP format: {}", ip);
            return false;
        }
    }
}
