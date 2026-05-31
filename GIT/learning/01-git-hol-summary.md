# Git Hands-On Learning - Summary

## HOL 1: Git Setup & First Repository

### Git Lifecycle Flow
```mermaid
graph LR
    A[Working Directory] -->|git add| B[Staging Area]
    B -->|git commit| C[Local Repository]
    C -->|git push| D[Remote Repository]
    D -->|git pull| A
```

### Configure Git
```mermaid
graph TD
    A[Install Git] --> B[Set username: git config --global user.name]
    A --> C[Set email: git config --global user.email]
    A --> D[Set editor: git config --global core.editor]
    B --> E[Verify: git config --list]
    C --> E
    D --> E
```

### Init & First Commit
```mermaid
sequenceDiagram
    participant Dev as Developer
    participant WD as Working Dir
    participant Stg as Staging Area
    participant LRepo as Local Repo
    participant RRepo as Remote Repo
    
    Dev->>WD: git init
    Dev->>WD: Create welcome.txt
    Dev->>Stg: git add welcome.txt
    Dev->>LRepo: git commit -m "first commit"
    Dev->>RRepo: git push origin master
```

---
## HOL 2: .gitignore

### How .gitignore Filters Files
```mermaid
flowchart TD
    A[All files in project] --> B{Matches .gitignore pattern?}
    B -->|Yes| C[Ignored - not tracked]
    B -->|No| D{Already tracked?}
    D -->|Yes| E[Tracked by Git]
    D -->|No| F[Untracked - shown in git status]
    
    G[.gitignore patterns] --> H[*.log - all .log files]
    G --> I[log/ - entire folder]
    G --> J[*.tmp - temp files]
    G --> K[build/ - build output]
```

```mermaid
graph LR
    subgraph Before .gitignore
        W1[welcome.txt] & L1[app.log] & L2[debug.log] & T1[temp.tmp]
    end
    subgraph After .gitignore with *.log
        W1 & T1
    end
    style L1 fill:#f96,stroke:#333
    style L2 fill:#f96,stroke:#333
```

---
## HOL 3: Branching & Merging

### Branch Structure Visualization
```mermaid
gitGraph
    commit id: "init"
    branch GitNewBranch
    checkout GitNewBranch
    commit id: "feature work"
    commit id: "more changes"
    checkout main
    merge GitNewBranch
    commit id: "after merge"
```

### Branch & Merge Commands Flow
```mermaid
flowchart TD
    subgraph Branching
        A[git branch GitNewBranch] --> B[git branch -a]
        B --> C[git switch GitNewBranch]
        C --> D[Add files & commit]
    end
    subgraph Merging
        E[git switch main] --> F[git diff main..GitNewBranch]
        F --> G[git merge GitNewBranch]
        G --> H[git log --oneline --graph --decorate]
        H --> I[git branch -d GitNewBranch]
    end
    D --> E
```

### Diff Visualization
```mermaid
graph LR
    subgraph Main Branch
        A[file1.txt: Hello]
    end
    subgraph GitNewBranch
        B[file1.txt: Hello World]
    end
    Main -->|git diff| C{Show differences}
    GitNewBranch -->|git diff| C
```

---
## HOL 4: Merge Conflict Resolution

### Conflict Scenario
```mermaid
gitGraph
    commit id: "base"
    branch GitWork
    checkout GitWork
    commit id: "add hello.xml with A"
    checkout main
    commit id: "add hello.xml with B"
    checkout GitWork
    commit id: "more work"
    checkout main
    merge GitWork tag: "CONFLICT!"
```

### Conflict Markers
```mermaid
graph TD
    subgraph File with Conflict
        A["<<<<<<< HEAD (Your changes)"] --> B["content from master"]
        B --> C["======="]
        C --> D["content from branch"]
        D --> E[">>>>>>> GitWork"]
    end
    
    subgraph Resolved
        F["Keep one version"]
    end
    
    A -.->|Edit manually| F
```

### Conflict Resolution Process
```mermaid
flowchart TD
    A[git merge GitWork] --> B{Conflict detected?}
    B -->|Yes| C[Open file with conflict markers]
    C --> D[Manually edit or use P4Merge]
    D --> E[Remove markers: <<<<  ==== >>>>]
    E --> F[git add resolved file]
    F --> G[git commit]
    G --> H[Add .orig to .gitignore]
    H --> I[git branch -d GitWork]
    B -->|No| J[Clean merge]
```

---
## HOL 5: Cleanup & Push to Remote

### Remote Sync Flow
```mermaid
sequenceDiagram
    participant Local as Local Repo
    participant Remote as Remote Repo
    
    Note over Local: git status (verify clean)
    Note over Local: git branch -a (list all)
    Local->>Remote: git pull origin master
    Remote-->>Local: Latest changes
    Local->>Remote: git push origin master
    Note over Remote: Changes reflected
```

### Complete Git Workflow Overview
```mermaid
flowchart LR
    subgraph Local
        WD[Working Directory] -->|git add| SA[Staging Area]
        SA -->|git commit| LR[Local Repo]
    end
    subgraph Remote
        RR[Remote Repo]
    end
    
    LR -->|git push| RR
    RR -->|git pull| LR
    
    LR -->|git branch| B1[Branch]
    B1 -->|git merge| LR
    B1 -->|git diff| LR
    
    B2[.gitignore] -.->|ignores files| WD
```

---
## Key Git Commands Reference

| Command | Purpose | Diagram |
|---------|---------|---------|
| `git init` | Initialize a new Git repo | Start of workflow |
| `git status` | Show working tree state | Checkpoint |
| `git add <file>` | Stage file for commit | WD → Staging |
| `git commit -m "msg"` | Commit staged changes | Staging → Local |
| `git branch <name>` | Create a new branch | Fork timeline |
| `git checkout <branch>` | Switch to a branch | Move pointer |
| `git merge <branch>` | Merge branch into current | Join timelines |
| `git pull origin main` | Fetch + merge from remote | Remote → Local |
| `git push origin main` | Push local → remote | Local → Remote |
| `git log --oneline --graph` | View commit history visually | Timeline view |
| `git diff` | Show differences | Compare versions |
