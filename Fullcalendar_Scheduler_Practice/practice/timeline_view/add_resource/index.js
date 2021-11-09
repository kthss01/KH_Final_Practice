document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    timeZone: 'UTC',
    headerToolbar: {
      left: 'promptResource today prev,next',
      center: 'title',
      right: 'resourceTimelineDay,resourceTimelineWeek'
    },
    customButtons: {
      promptResource: {
        text: '+ room',
        click: function() {
          var title = prompt('Room name');
          if (title) {
            calendar.addResource({
              title: title
            });
          }
        }
      }
    },
    // editable: true,
    aspectRatio: 1.5,
    initialView: 'resourceTimelineDay',
    resourceAreaHeaderContent: 'Rooms',
    resources: 'https://fullcalendar.io/demo-resources.json?with-nesting',
    events: 'https://fullcalendar.io/demo-events.json?single-day&for-resource-timeline'
  });

  calendar.render();
});