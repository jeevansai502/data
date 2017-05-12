#!/usr/bin/env bash

while [ -e *.zip ]; do
  files=*.zip;
  for file in $files; do
    echo -n "Cracking ${file}… ";
    output="$(fcrackzip -u -l 1-6 -c '1' *.zip | tr -d '\n')";
    password="${output/PASSWORD FOUND\!\!\!\!: pw == /}";
    if [ -z "${password}" ]; then
      echo "Failed to find password";
      break 2;
    fi;
    echo "Found password: \`${password}\`";
    unzip -q -P "${password}" "$file";
    rm "${file}";
  done;
done;
