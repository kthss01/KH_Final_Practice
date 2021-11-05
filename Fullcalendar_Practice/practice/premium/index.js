document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    selectable: true,
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true, // 이거만 하면 되는듯
    resources: [
      { id: 'a', title: 'Resource A' },
      { id: 'b', title: 'Resource B' }
    ],
    events: [
      { id: '1', title: 'Event 1', start: '2021-11-05', resourceId: 'a' }
    ],
    schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
    dateClick: function(info) {
      // alert('clicked ' + info.dateStr);

      // const title = prompt('title 입력');

      // if (title) {
      //   calendar.addEvent({
      //     title,
      //     start: info.date,
      //   });
        
      //   console.log(calendar.getEvents());
      // }

      // console.log(info);

    },
    select: function(info) {
      // alert('selected ' + info.startStr + ' to ' + info.endStr);

      // console.log(info);

      
      const title = prompt('title 입력');

      if (title) {
        calendar.addEvent({
          title,
          start: info.start,
          end: info.end,
          allDay: info.view.type === 'dayGridMonth',
          // backgroundColor: 'green',
        });
        
        // console.log(calendar.getEvents());
      }
    },
    eventClick: function(info) {
      console.log('Event: ' + info.event.title);

      // console.log(info.event.getResources()); // 안됨
      // var event = calendar.getEventById('1');
      // var resources = event.getResources();

      // console.log(resources);

      const { event } = info;
      const resources = event.getResources();
      console.log(resources);

    },
  });

  calendar.render();
});