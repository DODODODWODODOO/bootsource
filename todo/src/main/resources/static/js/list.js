// 체크박스 클릭 시 id 가져오기

// 화면의 중복 요소에 이벤트 작성
// document.querySelectorAll('[name="completed"]').addEventListener("click", (e) => {});

// 이벤트 전파 => 부모요소가 감지
document.querySelector(".list-group").addEventListener("click", (e) => {
  console.log("이벤트가 발생한 대상 " + e.target);
  console.log("이벤트가 발생한 대상 value " + e.target.value);
  console.log("이벤트가 감지한 대상 " + e.currentTarget);

  //   location.href = "/todo/update?id=" + e.target.value;  => get 방식
  const form = document.querySelector("#completedForm");
  form.querySelector('[name="id"]').value = e.target.value;
  form.submit();
});
