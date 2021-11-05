document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    eventClick: function(info) {
      var eventObj = info.event;

      if (eventObj.url) {
        alert(
          'Clicked ' + eventObj.title + '.\n' +
          'Will open ' + eventObj.url + ' in a new tab'
        );

        window.open(eventObj.url);

        info.jsEvent.preventDefault(); // prevents browser from following link in current tab.
      } else {
        alert('Clicked ' + eventObj.title);
      }

      // console.log(info.event);
      console.log(eventObj);
      const evt2 = calendar.getEventById('test');
      console.log(evt2);

      console.log('Event: ' + info.event.title);
      // console.log('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
      // console.log('View: ' + info.view.type);

      // change the border color just for fun
      // 이벤트 안에 backgroundColor는 이렇게 설정하면 적용 안됨
      // info.el.style.borderColor = 'red';
      // info.el.style.backgroundColor = 'blue';
      console.log(eventObj.backgroundColor);
      eventObj.backgroundColor = 'green';
      evt2.backgroundColor = 'green';
      // console.log(info.el.style.backgroundColor);

      info.event.title += 'test'; // 이런 식으로도 수정 안됨

    },
    initialDate: '2021-11-15',
    events: [
      {
        title: 'simple event',
        start: '2021-11-02'
      },
      {
        title: 'event with URL',
        url: 'https://www.google.com/',
        start: '2021-11-03'
      }
    ]
  });

  calendar.render();
});