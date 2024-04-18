// form submit 시 submit 기능 중지
document.querySelector("#createForm").addEventListener("submit", (e) => {
  e.preventDefault(); // 태그가 가진 기능 중지(a, submit, reset)

  // form 내용 가져오기 => javascript 객체 생성
  const data = {
    categoryName: document.querySelector("select[name='categoryName']").value,
    title: document.querySelector("#title").value,
    publisherName: document.querySelector("select[name='publisherName']").value,
    writer: document.querySelector("#writer").value,
    price: document.querySelector("#price").value,
    salePrice: document.querySelector("#salePrice").value,
  };

  console.log(data);

  // method 지정 않하면 get 으로 전송됨
  fetch(`http://localhost:8080/book/new`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data), // JSON.stringify() => javascript 객체 => json 타입으로 변환
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") alert("입력성공");
      location.href = "/book/list?page=1&type=&keyword=";
    });
});
