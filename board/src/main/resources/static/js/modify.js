document.querySelector(".btn-danger").addEventListener("click", () => {
  const form = document.querySelector("#actionForm");

  form.action = "/board/remove";
  form.submit();
});
