import pickle
import urlparse


class OSFile:
    def __init__(self):
        self.file = ""

    def getFileName(self):
        return self.file


class WindowsFile:
    def __init__(self):
        self.file = ""

    def getFileName(self):
        return self.file.lower()


class SourTaste:
    def __init__(self, request):
        self.request = request
        self.form = urlparse.parse_qs(request)

    def genResponse(self):
        response = ""

        __file = None
        ctype = "text/text"

        try:
            __file = pickle.loads(self.request)
        except Exception as e:
            print e
            print __name__
            __file = WindowsFile()

            if "inspect" in self.form:
                __file.file = self.form["inspect"][0]
            else:
                __file.file = "so_sour.html"
                ctype = "text/html"

        f = __file.getFileName().replace("/","").replace("\\","").strip()

        if "flag" in f:
            response += "Bad hacker!!!\n"

            return response, ctype

        try:
            with open("/home/5hub4m/Sour Taste/" + f.lower(), "r") as p_file:
                for lines in p_file:
                    response += lines
        except:
            response += "Go away, there's nothing here!\n"

        return response, ctype


def webapp(environ, start_response):
    if environ['REQUEST_METHOD'] == 'POST':
        try:
            request_body_size = int(environ.get('CONTENT_LENGTH', 0))
        except:
            request_body_size = 0

        request = environ['wsgi.input'].read(request_body_size)
    else:
        request = environ.get('QUERY_STRING', '')

    app = SourTaste(request)
    body, ctype = app.genResponse()

    status = '200 OK'
    response_headers = [
        ('Content-Type', ctype),
        ('Content-Length', str(len(body)))
    ]

    start_response(status, response_headers)
    return [body.encode('utf-8')]

