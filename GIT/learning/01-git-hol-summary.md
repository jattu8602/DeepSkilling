# Git Hands-On Learning - Summary

## HOL 1: Git Setup & First Repository
- **Configure Git**: `git config --global user.name "Your Name"` and `git config --global user.email "your.email@example.com"`
- **Integrate editor** (notepad++): Set as default editor via `git config --global core.editor notepad++`
- **Init repo**: `git init` to create a new Git repository
- **Check status**: `git status` to see working directory state
- **Add files**: `git add <file>` to stage files
- **Commit**: `git commit -m "message"` to commit staged changes
- **Remote**: `git pull origin master` and `git push origin master` to sync with remote (GitLab/GitHub)

## HOL 2: .gitignore
- Create a `.gitignore` file in the repo root
- Add patterns to ignore files/folders (e.g., `*.log` ignores all .log files, `log/` ignores a log folder)
- `git status` will no longer show ignored files
- Helps keep the repo clean by excluding build artifacts, logs, temp files, etc.

## HOL 3: Branching & Merging
- **Create branch**: `git branch <branch-name>`
- **List branches**: `git branch -a` (local + remote)
- **Switch branch**: `git checkout <branch-name>` or `git switch <branch-name>`
- **Diff**: `git diff` shows differences between branches
- **Merge**: `git merge <source-branch>` (while on target branch, e.g., master)
- **View log**: `git log --oneline --graph --decorate` to see branch/merge history
- **Delete branch**: `git branch -d <branch-name>` after merging

## HOL 4: Merge Conflict Resolution
- Conflicts occur when two branches modify the same file differently
- Steps to resolve:
  1. `git merge` will show conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`)
  2. Manually edit the file to resolve conflicts
  3. Use a merge tool (e.g., P4Merge) for visual diff
  4. `git add <resolved-file>` then `git commit` to finalize merge
- Backup files (`.orig`) can be added to `.gitignore`

## HOL 5: Cleanup & Push to Remote
- Verify clean state with `git status`
- List all branches with `git branch -a`
- `git pull origin master` to sync latest remote changes
- `git push origin master` to push local commits to remote
- Ensure the remote repository reflects all local changes

---

## Key Git Commands Reference

| Command | Purpose |
|---------|---------|
| `git init` | Initialize a new Git repo |
| `git status` | Show working tree status |
| `git add <file>` | Stage file(s) for commit |
| `git commit -m "msg"` | Commit staged changes |
| `git branch <name>` | Create a new branch |
| `git checkout <branch>` | Switch to a branch |
| `git merge <branch>` | Merge branch into current |
| `git pull origin master` | Fetch and merge from remote |
| `git push origin master` | Push local commits to remote |
| `git log --oneline --graph --decorate` | View commit history visually |
| `git diff` | Show differences |
| `git branch -d <name>` | Delete a merged branch |
