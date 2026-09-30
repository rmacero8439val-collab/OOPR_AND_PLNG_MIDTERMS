import copy

class Ticket:
    def __init__(self, tickets):
        self.t = tickets
        self.idx = 0
    def __iter__(self): return self
    def __next__(self):
        while self.idx < len(self.t):
            tk = self.t[self.idx]
            self.idx += 1
            if tk['status'] == 'waiting': return tk
        raise StopIteration

db, t_cnt, streak = [], 1, 0
counters = {i: {"status": "free", "tk_id": None} for i in range(1, 3)}

def clean_input(purp, s_type):
    """Maps inputs flexibly (accepts numbers, text, lowercase, uppercase)"""
    p_clean = str(purp).strip().lower()
    t_clean = str(s_type).strip().lower()
    
    # Map Purpose
    if p_clean in ["1", "enrollment"]: final_p = "Enrollment"
    elif p_clean in ["2", "records"]: final_p = "Records"
    elif p_clean in ["3", "payment"]: final_p = "Payment"
    else: raise ValueError(f"Invalid Purpose: '{purp}'. Choose 1, 2, 3 or Enrollment, Records, Payment.")
    
    # Map Type
    if t_clean in ["", "1", "regular"]: final_t = "regular"
    elif t_clean in ["2", "priority"]: final_t = "priority"
    else: raise ValueError(f"Invalid Type: '{s_type}'. Choose 1, 2, or regular, priority.")
    
    return final_p, final_t

def issue_ticket(purp, s_type="regular"):
    global t_cnt
    final_purp, final_type = clean_input(purp, s_type)
    db.append({"id": t_cnt, "purpose": final_purp, "type": final_type, "status": "waiting"})
    t_cnt += 1
    return t_cnt - 1

def issue_many(*reqs):
    # Validates input list atomically
    for r in reqs:
        if not isinstance(r, (list, tuple)) or len(r) < 2: raise ValueError("Invalid format")
        clean_input(r[0], r[1])
    return [issue_ticket(r[0], r[1]) for r in reqs]

def next_ticket(w_list, current_streak):
    wait = [t for t in w_list if t['status'] == 'waiting']
    if not wait: return None, None
    pri = [t for t in wait if t['type'] == 'priority']
    reg = [t for t in wait if t['type'] == 'regular']
    sel = reg if pri and reg and current_streak >= 2 else (pri if pri else reg)
    # Find matching original index
    for idx, item in enumerate(w_list):
        if item['id'] == sel[0]['id']: return item, idx
    return None, None

def simulate_pos(target_id):
    tmp_db, tmp_stk, steps = copy.deepcopy(db), streak, 0
    tk = next((t for t in tmp_db if t['id'] == target_id), None)
    if not tk or tk['status'] != 'waiting': return "N/A"
    while True:
        t, idx = next_ticket(tmp_db, tmp_stk)
        if not t: return "Unknown"
        steps += 1
        if t['id'] == target_id: return steps
        tmp_stk = tmp_stk + 1 if t['type'] == 'priority' else 0
        tmp_db[idx]['status'] = 'served'

def report(**kwargs):
    print("\n" + "="*30 + "\n     SUMMARY REPORT\n" + "="*30)
    for k, v in kwargs.items(): print(f" * {k.upper():<15}: {v}")

def run_tests():
    global db, t_cnt, streak, counters
    db, t_cnt, streak = [], 1, 0
    assert issue_ticket("1", "regular") == 1 and issue_ticket("Records", "2") == 2
    issue_ticket("3", "1")
    tk, _ = next_ticket(db, 2)
    assert tk['id'] == 3, "Fairness rule broken"
    counters = {1: {"status": "busy", "tk_id": 9}, 2: {"status": "busy", "tk_id": 10}}
    db[0]['status'] = 'cancelled'
    tk_res, _ = next_ticket(db, 0)
    assert tk_res['id'] == 2
    it = Ticket(db)
    assert next(it)['id'] == 2 and next(it)['id'] == 3
    try: next(it); assert False
    except StopIteration: print(">> All 5 automated scenario check assertions passed.")

while True:
    print("\n1.Issue 2.Call 3.Complete 4.Cancel 5.Waiting 6.Counters 7.Report 8.Exit")
    ch = input("Action: ").strip()
    if ch == '1':
        p = input("Purpose (1=Enrollment, 2=Records, 3=Payment): ")
        t = input("Type (1=regular, 2=priority): ")
        try: print(f"Ticket #{issue_ticket(p, t)} created.")
        except ValueError as e: print(e)
    elif ch == '2':
        cid = next((i for i in sorted(counters.keys()) if counters[i]["status"] == "free"), None)
        if not cid: print("All counters busy."); continue
        tk, idx = next_ticket(db, streak)
        if tk:
            db[idx]['status'], counters[cid]["status"], counters[cid]["tk_id"] = 'served', 'busy', tk['id']
            streak = streak + 1 if tk['type'] == 'priority' else 0
            print(f"Ticket #{tk['id']} assigned to Counter {cid}")
        else: print("No one waiting.")
    elif ch == '3':
        try:
            cid = int(input("Counter ID (1/2): "))
            if cid in counters and counters[cid]["status"] == "busy":
                print(f"Finished #{counters[cid]['tk_id']}"); counters[cid]["status"], counters[cid]["tk_id"] = "free", None
            else: print("Invalid counter state.")
        except ValueError: print("Enter numeric values.")
    elif ch == '4':
        try:
            tid = int(input("Ticket ID: "))
            tk = next((t for t in db if t['id'] == tid and t['status'] == 'waiting'), None)
            if tk: tk['status'] = 'cancelled'; print("Cancelled.")
            else: print("Ticket not found/waiting.")
        except ValueError: print("Enter numeric values.")
    elif ch == '5':
        for i, tk in enumerate(Ticket(db), 1):
            print(f"[{i}] #{tk['id']} ({tk['type']}) - Est. Calls: {simulate_pos(tk['id'])}")
    elif ch == '6':
        for k, v in counters.items(): print(f"Counter {k}: {v['status'].upper()} (Tk #{v['tk_id']})")
    elif ch == '7':
        report(wait=len([t for t in db if t['status']=='waiting']), served=len([t for t in db if t['status']=='served']), free_counters=len([c for c in counters.values() if c['status']=='free']))
    elif ch == '8': run_tests(); break
