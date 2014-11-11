<html>
    <body>
        <div class="col-md-2 col-md-offset-5">
            <H2>Welcome!</H2>
            
            <% if (session.getAttribute("member") == null) { %>
                <a href="/login">Click Here to Login </a><br/><br/>
            <% } else { %>
                <a href="/logout">Click Here to Logout </a><br/><br/>
            <% } %>
            
        </div>
    </body>
</html>
