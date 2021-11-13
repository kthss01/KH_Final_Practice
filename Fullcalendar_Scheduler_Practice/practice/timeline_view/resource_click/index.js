document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
      left: 'today prev,next',
      center: 'title',
      right: 'resourceTimelineDay,resourceTimelineWeek'
    },
    aspectRatio: 1.6,
    initialView: 'resourceTimelineDay',
    resourceGroupField: 'building',
    resources: [
      { id: 'a', building: '460 Bryant', title: 'Auditorium A' },
      { id: 'b', building: '460 Bryant', title: 'Auditorium B' },
      { id: 'c', building: '460 Bryant', title: 'Auditorium C' },
      { id: 'd', building: '460 Bryant', title: 'Auditorium D' },
      { id: 'e', building: '460 Bryant', title: 'Auditorium E' },
      { id: 'f', building: '460 Bryant', title: 'Auditorium F' },
      { id: 'g', building: '564 Pacific', title: 'Auditorium G' },
      { id: 'h', building: '564 Pacific', title: 'Auditorium H' },
      { id: 'i', building: '564 Pacific', title: 'Auditorium I' },
      { id: 'j', building: '564 Pacific', title: 'Auditorium J' },
      { id: 'k', building: '564 Pacific', title: 'Auditorium K' },
      { id: 'l', building: '564 Pacific', title: 'Auditorium L' },
      { id: 'm', building: '564 Pacific', title: 'Auditorium M' },
      { id: 'n', building: '564 Pacific', title: 'Auditorium N' },
      { id: 'o', building: '101 Main St', title: 'Auditorium O' },
      { id: 'p', building: '101 Main St', title: 'Auditorium P' },
      { id: 'q', building: '101 Main St', title: 'Auditorium Q' },
      { id: 'r', building: '101 Main St', title: 'Auditorium R' },
      { id: 's', building: '101 Main St', title: 'Auditorium S' },
      { id: 't', building: '101 Main St', title: 'Auditorium T' },
      { id: 'u', building: '101 Main St', title: 'Auditorium U' },
      { id: 'v', building: '101 Main St', title: 'Auditorium V' },
      { id: 'w', building: '101 Main St', title: 'Auditorium W' },
      { id: 'x', building: '101 Main St', title: 'Auditorium X' },
      { id: 'y', building: '101 Main St', title: 'Auditorium Y' },
      { id: 'z', building: '101 Main St', title: 'Auditorium Z' }
    ],
    selectable: true,
    select: function(info) {
      alert('selected ' + info.startStr + ' to ' + info.endStr + ' on resource ' + info.resource.id);
    },
    resourceGroupLabelDidMount: function(arg) {
      arg.el.addEventListener('click', function() {
        // console.log(arg)
        // console.log(arg.resource) // 여긴 resource 없음
        console.log(arg.groupValue)
      })
    },
    resourceLabelDidMount: function(arg) {
      arg.el.addEventListener('click', function() {
        // console.log(arg)
        // console.log(arg.resource)
        console.log(arg.resource.id)
        console.log(arg.resource.title)
        console.log(arg.resource.eventBackgroundColor)
      })
    },
  });

  calendar.render();
});