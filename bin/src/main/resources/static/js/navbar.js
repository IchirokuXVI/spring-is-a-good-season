$(() => {
  const sidebar = $('#sidebar');
  const sidebarWidth = $(sidebar).width();
  $(sidebar).width(sidebarWidth);
  const sidebarLargestSpan = Math.max(...$(sidebar).find('li a span').map(function() { return $(this).outerWidth(true)}));
  $(sidebar).on('mouseenter', (e) => {
    $(e.currentTarget).width(sidebarWidth + sidebarLargestSpan + 5);
  });

  $(sidebar).on('mouseleave', (e) => {
    $(e.currentTarget).width(sidebarWidth);
  });
});