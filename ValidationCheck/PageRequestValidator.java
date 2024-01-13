@Component
@Slf4j
public class PageRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("supports : {}", PageRequest.class.equals(clazz));
        return PageRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("validate 수행");
        PageRequest myRequestDTO = (PageRequest) target;

        // 필드 유효성 검사 규칙 정의
        if (myRequestDTO.getPage() < 1) {
            errors.rejectValue("page", "page.wrong", "page should be above 1");
        }


        if (!myRequestDTO.getDesc().equalsIgnoreCase("ASC") && !myRequestDTO.getDesc().equalsIgnoreCase("DESC")) {
            errors.rejectValue("desc", "desc.wrong", "desc should be asc(ASC) or desc(DESC)");
        }
    }
}
