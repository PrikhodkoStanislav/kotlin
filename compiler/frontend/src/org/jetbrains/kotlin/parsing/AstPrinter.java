/*
 * Copyright 2010-2017 JetBrains s.r.o.
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

package org.jetbrains.kotlin.parsing;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.util.Arrays;

public class AstPrinter {

    private static final String FILENAME = "test.json";

    private final ASTNode root;

    public AstPrinter(ASTNode root) {
        this.root = root;
    }

    @NotNull
    private JsonArray walk(ArrayList<ASTNode> nodes, JsonArray jsonNodes) {
        for(ASTNode node : nodes) {
            JsonObject jsonNode = new JsonObject();
            jsonNode.addProperty("type", node.getElementType().toString());

            ArrayList<ASTNode> children = new ArrayList<ASTNode>(
                    Arrays.asList(node.getChildren(null)));
            if (children.size() != 0) {
                jsonNode.add(
                        "children",
                        this.walk(children, new JsonArray()));
            }

            jsonNodes.add(jsonNode);
        }

        return jsonNodes;
    }

    public void print() {
        ArrayList<ASTNode> root = new ArrayList<>();
        root.add(this.root);

        JsonArray astJson = this.walk(root, new JsonArray());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            bw.write(astJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
