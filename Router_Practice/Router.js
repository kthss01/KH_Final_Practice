class Router {
  nowPage = ''

  constructor({ pages }) {
    this.app = document.getElementById('app');

    window.onhashchange = () => {
      // console.log('변경되면 나옴');
      this.pages = pages;
      this.nowPage = window.location.hash.replace('#', '');
      console.log(this.nowPage);

      console.log(this.pages);
      const page = this.pages.find((page) => page.path === this.nowPage);
      console.log(page);
      const Page = page.page;
      const currentPage = new Page({ router: this });
      console.log(currentPage);

      this.app.innerHTML = currentPage.render();
      currentPage.mount();
    }
  }

  push(pageName) {
    window.location.hash = pageName;
  }

}

module.exports = Router;