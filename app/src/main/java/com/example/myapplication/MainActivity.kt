package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etEnterAnagram = binding.etEnterAnagram
        val btnSave = binding.btnSave
        val btnOutput = binding.btnOutput
        val anagramsSet = mutableSetOf<String>()

        btnSave.setOnClickListener {
            if (validInput(etEnterAnagram.text.toString())) {
                anagramsSet.add(etEnterAnagram.text.toString())
            } else {
                Toast.makeText(this, "Invalid input, Please enter a valid anagram.", Toast.LENGTH_SHORT).show()
            }
        }

        btnOutput.setOnClickListener {
            val groupedAnagrams = groupAnagrams(anagramsSet)
            updateTextView(groupedAnagrams)
        }
    }

    private fun validInput(input: String): Boolean {
        return input.isNotEmpty()
    }

    private fun groupAnagrams(anagrams: Set<String>): List<List<String>> {
        val groupedAnagrams = mutableMapOf<String, MutableList<String>>()

        for (anagram in anagrams) {
            val sortedAnagram = sortChars(anagram)
            if (!groupedAnagrams.containsKey(sortedAnagram)) {
                groupedAnagrams[sortedAnagram] = mutableListOf(anagram)
            } else {
                groupedAnagrams[sortedAnagram]?.add(anagram)
            }
        }

        return ArrayList(groupedAnagrams.values)
    }

    private fun sortChars(str: String): String {
        val sortedChars = str.toCharArray().sorted()
        return String(sortedChars.toCharArray())
    }

    private fun updateTextView(groupedAnagrams: List<List<String>>) {
        for (group in groupedAnagrams) {
            println(group)
        }

        val containerCount = groupedAnagrams.size
        val tvAnagramCount = binding.tvAnagramCount
        tvAnagramCount.text = "Anagram Count: $containerCount \n Anagrams Inserted : \n $groupedAnagrams"
    }
}
