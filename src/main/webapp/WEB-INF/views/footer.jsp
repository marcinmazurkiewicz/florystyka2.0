<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer >
    <p class="align-r">Florystyka 2.0 &copy; 2017 <a class="red-text text-accent-2" href="http://dudek.io">Marcin Dudek</a></p>
</footer>


<script src=" <c:url value="/resources/js/jquery.js" /> " ></script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src=" <c:url value="/resources/js/materialize.js" /> " ></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.parallax');
        var instances = M.Parallax.init(elems);
    });

    // Or with jQuery

    $(document).ready(function(){
        $('.parallax').parallax();
    });
</script>
</body>
</html>