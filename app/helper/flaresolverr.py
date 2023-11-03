from typing import List

from fspy import FlareSolverr


class FlareSolverrHelper():
    def __init__(self, solver_host: str, solver_port: int):
        self.solver = FlareSolverr(host=solver_host, port=str(solver_port))

    def get(self, url: str, cookies: List[dict]):
        return self.solver.request_get(url, cookies=cookies)

    def session_create(self, session_id: str):
        return self.solver.create_session(session_id)

    def session_destory(self, session_id: str):
        return self.solver.destroy_session(session_id)