/*
 * Copyright 2010-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PsiPrinter {
    private final ASTNode root;
    private final VirtualFile file;

    public PsiPrinter(ASTNode root, VirtualFile file) {
        this.root = root;
        this.file = file;
    }

    @NotNull
    private JsonArray walk(List<ASTNode> nodes, JsonArray jsonNodes) {
        for(ASTNode node : nodes) {
            JsonObject jsonNode = new JsonObject();
            jsonNode.addProperty("type", node.getElementType().toString());

            List<ASTNode> children;

            try {
                children = Arrays.asList(node.getChildren(null));
            } catch (ClassCastException e) {
                children = null;
            }
            if (children != null && children.size() != 0) {
                jsonNode.add(
                        "children",
                        this.walk(children, new JsonArray()));
            }

            jsonNodes.add(jsonNode);
        }

        return jsonNodes;
    }

    public void print() {
        List<ASTNode> root = new ArrayList<>();
        root.add(this.root);

        JsonArray psiJson = this.walk(root, new JsonArray());
        File psiJsonFile = new File(file.getPath() + ".json");

        try {
            psiJsonFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(psiJsonFile))) {
            bw.write(psiJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
