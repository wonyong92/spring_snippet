@InitBinder("pageRequest")//validator를 적용할 model의 변수명을 지정 - @modelAttribute 대상 변수
protected void pageRequestMessageBinder(WebDataBinder binder) {//@InitBinder 어노테이션이 설정된 경우 자동으로 주입 수행
    binder.addValidators(pageRequestValidator); //model의 변수명에 대해 validator 구현체를 설정
}
